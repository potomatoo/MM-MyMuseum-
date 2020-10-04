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


def update_score():
    pass
    # print('hi')
    # # CSV파일 불러오기
    # art_table = pd.read_csv('../../../recommend/art_score.csv')
    # art_table.fillna('NaN', inplace=True)

    # # # Mysql에서 데이터 불러오기
    # db_connection = sql.connect(host='j3b205.p.ssafy.io', port="3306", database='test', user='root', password='root')
    # db_cursor = db_connection.cursor()
    # db_cursor.execute("SELECT * FROM event")
    # events = db_cursor.fetchall()
    # print(events)

    # # # 신규 이용자라면 칼럼 추가
    # for event in events:
    #     if event[0] not in art_table.columns:
    #         art_table[event[0]] = 0

    # # # 가중치 설정
    # TARGET_WEIGHT = 0.2
    # GENRE_WEIGHT = 0.05
    # ARTIST_WEIGHT = 0.1
    # TYPE_WEIGHT = 0.03
    # METHOD_WEIGHT = 0.02
    # MUSEUM_WEIGHT = 0.03
    # COLOR_WEIGHT = 0.02

    # for event in events:
    #     # 클릭 종류에 따른 가중치
    #     if event[2] == 0:
    #         CLICK_WEIGHT = 1
    #     elif event[2] == 1:
    #         CLICK_WEIGHT = 1.1
    #     elif event[2] == 2:
    #         CLICK_WEIGHT = 1.2
    #     elif event[2] == 3:
    #         CLICK_WEIGHT = 1.3
    #     # 타겟 가중치
    #     art_table.loc[event[1], event[0]] += (TARGET_WEIGHT * CLICK_WEIGHT)

    #     # 장르 가중치
    #     arr_genre = art_table[art_table['art_genre'] == art_table.loc[event[1], 'art_genre']].index
    #     art_table.loc[arr_genre, event[0]] += (GENRE_WEIGHT * CLICK_WEIGHT)
    #     art_table.loc[event[1], event[0]] -= (GENRE_WEIGHT * CLICK_WEIGHT)

    #     # 작가 가중치
    #     arr_artist = art_table[art_table['art_artist'] == art_table.loc[event[1], 'art_artist']].index
    #     art_table.loc[arr_artist, event[0]] += (ARTIST_WEIGHT * CLICK_WEIGHT)
    #     art_table.loc[event[1], event[0]] -= (ARTIST_WEIGHT * CLICK_WEIGHT)

    #     # 타입 가중치
    #     arr_type = art_table[art_table['art_type'] == art_table.loc[event[1], 'art_type']].index
    #     art_table.loc[arr_type, event[0]] += (TYPE_WEIGHT * CLICK_WEIGHT)
    #     art_table.loc[event[1], event[0]] -= (TYPE_WEIGHT * CLICK_WEIGHT)

    #     # 재료 가중치
    #     arr_method = art_table[art_table['art_method'] == art_table.loc[event[1], 'art_method']].index
    #     art_table.loc[arr_method, event[0]] += (METHOD_WEIGHT * CLICK_WEIGHT)
    #     art_table.loc[event[1], event[0]] -= (METHOD_WEIGHT * CLICK_WEIGHT)

    #     # 박물관 가중치
    #     arr_museum = art_table[art_table['art_museum'] == art_table.loc[event[1], 'art_museum']].index
    #     art_table.loc[arr_museum, event[0]] += (MUSEUM_WEIGHT * CLICK_WEIGHT)
    #     art_table.loc[event[1], event[0]] -= (MUSEUM_WEIGHT * CLICK_WEIGHT)

    #     # 색 분류 가중치
    #     # arr_color = art_table[art_table['art_color'] == art_table.loc[event[1], 'art_color']].index
    #     # art_table.loc[arr_color, event[0]] += (COLOR_WEIGHT * CLICK_WEIGHT)
    #     # art_table.loc[event[1], event[0]] -= (COLOR_WEIGHT * CLICK_WEIGHT)
    #     print('in')

    
    # # # Mysql에서 event 테이블의 행 삭제
    # db_cursor.execute("DELETE FROM event")
    # db_connection.commit()
    # db_cursor.close()
    # db_connection.close()

    # # # CSV파일 갱신
    # # art_table.to_csv('../../../recommend/art_score.csv', encoding="utf-8-sig", index=False)

    # # 일정 시간마다 함수 실행
    # threading.Timer(5, update_score).start()


@api_view(['GET'])
def recommend_art(request, user_id):
    art_table = pd.read_csv('../../../recommend/art_score.csv')
    art_table.fillna('NaN', inplace=True)

    sort_table = art_table.sort_values(by=user_id, ascending=False)

    recommend_list = []

    high = 60
    middle = 30
    rand = 10

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
    print(len(recommend_list))
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

update_score()