from django.shortcuts import render, get_object_or_404

from rest_framework.response import Response
from rest_framework.decorators import api_view

from .models import Art
from .serializers import ArtSerializer

import mysql.connector as sql
# import sqlalchemy as sqla
import pandas as pd
import numpy as np
import random

import threading
import schedule
import time

import jwt

SECRET_KEY = 'i3b107'
ALGORITHM = 'HS512'

def update_score():
    # CSV file load
    art_table = pd.read_csv('../../../recommend/art_score.csv')
    art_table.fillna('NaN', inplace=True)

    # Mysql load
    db_connection = sql.connect(host='j3b205.p.ssafy.io', port="3306", database='ssafy', user='root', password='root')
    db_cursor = db_connection.cursor()
    db_cursor.execute("SELECT * FROM log")
    events = db_cursor.fetchall()

    # add columns
    for event in events:
        if event[1] not in art_table.columns:
            art_table[event[1]] = 0

    # weight setting
    TARGET_WEIGHT = 0.2
    GENRE_WEIGHT = 0.05
    ARTIST_WEIGHT = 0.1
    TYPE_WEIGHT = 0.03
    METHOD_WEIGHT = 0.02
    MUSEUM_WEIGHT = 0.03
    COLOR_WEIGHT = 0.02

    for event in events:
        # click weight
        if event[3] == 0:
            CLICK_WEIGHT = 1.4
        elif event[3] == 1:
            CLICK_WEIGHT = 1
        elif event[3] == 2:
            CLICK_WEIGHT = 1.15
        elif event[3] == 3:
            CLICK_WEIGHT = 1.3
        # target weight
        art_table.loc[event[2], event[1]] += (TARGET_WEIGHT * CLICK_WEIGHT)

        # genre weight
        arr_genre = art_table[art_table['art_genre'] == art_table.loc[event[2], 'art_genre']].index
        art_table.loc[arr_genre, event[1]] += (GENRE_WEIGHT * CLICK_WEIGHT)
        art_table.loc[event[2], event[1]] -= (GENRE_WEIGHT * CLICK_WEIGHT)

        # artist weight
        arr_artist = art_table[art_table['art_artist'] == art_table.loc[event[2], 'art_artist']].index
        art_table.loc[arr_artist, event[1]] += (ARTIST_WEIGHT * CLICK_WEIGHT)
        art_table.loc[event[2], event[1]] -= (ARTIST_WEIGHT * CLICK_WEIGHT)

        # type weight
        arr_type = art_table[art_table['art_type'] == art_table.loc[event[2], 'art_type']].index
        art_table.loc[arr_type, event[1]] += (TYPE_WEIGHT * CLICK_WEIGHT)
        art_table.loc[event[2], event[1]] -= (TYPE_WEIGHT * CLICK_WEIGHT)

        # method weight
        arr_method = art_table[art_table['art_method'] == art_table.loc[event[2], 'art_method']].index
        art_table.loc[arr_method, event[1]] += (METHOD_WEIGHT * CLICK_WEIGHT)
        art_table.loc[event[2], event[1]] -= (METHOD_WEIGHT * CLICK_WEIGHT)

        # museum weight
        arr_museum = art_table[art_table['art_museum'] == art_table.loc[event[2], 'art_museum']].index
        art_table.loc[arr_museum, event[1]] += (MUSEUM_WEIGHT * CLICK_WEIGHT)
        art_table.loc[event[2], event[1]] -= (MUSEUM_WEIGHT * CLICK_WEIGHT)

        # color weight
        arr_color = art_table[art_table['art_color'] == art_table.loc[event[2], 'art_color']].index
        art_table.loc[arr_color, event[1]] += (COLOR_WEIGHT * CLICK_WEIGHT)
        art_table.loc[event[2], event[1]] -= (COLOR_WEIGHT * CLICK_WEIGHT)

    
    # delete tuple
    db_cursor.execute("DELETE FROM log")
    db_connection.commit()
    db_cursor.close()
    db_connection.close()

    # CSV file update
    art_table.to_csv('../../../recommend/art_score.csv', encoding="utf-8-sig", index=False)

    # routine
    threading.Timer(10, update_score).start()


@api_view(['GET'])
def recommend_art(request):
    # csv file load
    art_table = pd.read_csv('../../../recommend/art_score.csv')
    art_table.fillna('NaN', inplace=True)

    if request.headers.get('Authorization'):
        # jwt token decoding
        token_str = request.headers.get('Authorization')
        payload = jwt.decode(token_str[7:], SECRET_KEY, ALGORITHM)
        user_id = payload['sub']

        if user_id in art_table.columns:
            # sorting by user_id
            sort_table = art_table.sort_values(by=user_id, ascending=False)

            recommend_list = []

            # count setting
            high = 60
            middle = 30
            rand = 10

            # high score
            high_list = list(sort_table.index[:300])
            high_select = random.sample(high_list, high)
            recommend_list.extend(high_select)
            
            # middle score
            middle_list = list(sort_table.index[10000:10300])
            middle_select = random.sample(middle_list, middle)
            recommend_list.extend(middle_select)

            # random
            random_select = random.sample(range(39740), rand)
            recommend_list.extend(random_select)

            recommend_list = list(set(recommend_list))

            arts = Art.objects.filter(art_no__in=recommend_list)
            serializer = ArtSerializer(arts, many=True)

            # setting log type
            for i in range(len(recommend_list)):
                if serializer.data[i]['art_no'] in high_select:
                    serializer.data[i]['log_type'] = 1
                elif serializer.data[i]['art_no'] in middle_select:
                    serializer.data[i]['log_type'] = 2
                elif serializer.data[i]['art_no'] in random_select:
                    serializer.data[i]['log_type'] = 3

            return Response(serializer.data)
        else:
            art_table['scoreSum'] = art_table.iloc[:, 12:].sum(axis=1)
            sort_table = art_table.sort_values(by='scoreSum', ascending=False)
            
            high_list = list(sort_table.index[:400])
            recommend_list = random.sample(high_list, 100)

            arts = Art.objects.filter(art_no__in=recommend_list)
            serializer = ArtSerializer(arts, many=True)

            # setting log type
            for i in range(len(recommend_list)):
                serializer.data[i]['log_type'] = 0

            return Response(serializer.data)
    else:
        art_table['scoreSum'] = art_table.iloc[:, 12:].sum(axis=1)
        sort_table = art_table.sort_values(by='scoreSum', ascending=False)
        
        high_list = list(sort_table.index[:400])
        recommend_list = random.sample(high_list, 100)

        arts = Art.objects.filter(art_no__in=recommend_list)
        serializer = ArtSerializer(arts, many=True)

        return Response(serializer.data)

@api_view(['GET'])
def recommend_yellow(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'YELLOW'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_blue(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'BLUE'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_brown(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'BROWN'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_green(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'GREEN'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_black(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'BLACK'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_teal(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'TEAL'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_red(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'RED'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    print(len(arr_select))
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_orange(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'ORANGE'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_pink(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'PINK'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, len(arr_color))
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_gray(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'GRAY'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_white(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'BROWN'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, 50)
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)

@api_view(['GET'])
def recommend_purple(request):
    art_table = pd.read_csv('../../../recommend/arts.csv')
    arr_color = art_table[art_table['art_color'] == 'PURPLE'].index
    arr_color = list(arr_color)
    arr_select = random.sample(arr_color, len(arr_color))
    arts = Art.objects.filter(art_no__in=arr_select)
    serializer = ArtSerializer(arts, many=True)
    for i in range(len(arr_select)):
        serializer.data[i]['log_type'] = 0

    return Response(serializer.data)


update_score()