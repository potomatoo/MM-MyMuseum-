from django.shortcuts import render, get_object_or_404

from rest_framework.response import Response
from rest_framework.decorators import api_view

from .models import Art
from .serializers import ArtSerializer

# import mysql.connector as sql
# import sqlalchemy as sqla
import pandas as pd
import numpy as np
import random

import threading
import schedule
import time


# def update_score():
    # CSV파일 불러오기
    # art_table = pd.read_csv('../../../recommend/new_table.csv')
    # art_table.fillna('NaN', inplace=True)

    # # Mysql에서 데이터 불러오기
    # db_connection = sql.connect(host='j3b205.p.ssafy.io', port="3306", database='test', user='root', password='root')
    # db_cursor = db_connection.cursor()
    # db_cursor.execute("SELECT * FROM event")
    # events = db_cursor.fetchall()

    # # 신규 이용자라면 칼럼 추가
    # for event in events:
    #     if event[0] not in art_table.columns:
    #         art_table[event[0]] = 0

    # print(art_table.head())

    # threading.Timer(10, update_score).start()

    # art_table.to_csv('./new_table.csv', encoding="utf-8-sig", index=False)

@api_view(['GET'])
def recommend_art(request, user_id):
    art_table = pd.read_csv('../../../recommend/art_score.csv')
    art_table.fillna('NaN', inplace=True)

    sort_table = art_table.sort_values(by=user_id, ascending=False)

    recommend_list = []

    high = 6
    middle = 3
    rand = 1

    high_list = list(sort_table.index[:300])
    high_select = random.sample(high_list, high)
    recommend_list.extend(high_select)
    # print('high', high_select)

    middle_list = list(sort_table.index[10000:10300])
    middle_select = random.sample(middle_list, middle)
    recommend_list.extend(middle_select)
    # print('middle', middle_select)

    random_select = random.sample(range(39740), rand)
    recommend_list.extend(random_select)
    # print('random', random_select)

    recommend_list = list(set(recommend_list))

    arts = Art.objects.filter(art_no__in=recommend_list)
    serializer = ArtSerializer(arts, many=True)

    for i in range(len(recommend_list)):
        if serializer.data[i]['art_no'] in high_select:
            serializer.data[i]['click_type'] = 1
        elif serializer.data[i]['art_no'] in middle_select:
            serializer.data[i]['click_type'] = 2
        elif serializer.data[i]['art_no'] in random_select:
            serializer.data[i]['click_type'] = 3
            
    return Response(serializer.data)
    
# schedule.every(10).seconds.do(update_score)

# while True:
#     schedule.run_pending()
#     time.sleep(1)

# update_score()