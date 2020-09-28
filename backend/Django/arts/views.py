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

# import threading
import schedule
import time


def update_score():
    # CSV파일 불러오기
    art_table = pd.read_csv('./new_table.csv')
    art_table.fillna('NaN', inplace=True)

    # # Mysql에서 데이터 불러오기
    # db_connection = sql.connect(host='j3b205.p.ssafy.io', port="3306", database='test', user='root', password='root')
    # db_cursor = db_connection.cursor()
    # db_cursor.execute("SELECT * FROM event")
    # events = db_cursor.fetchall()

    # # 신규 이용자라면 칼럼 추가
    # for event in events:
    #     if event[0] not in art_table.columns:
    #         art_table[event[0]] = 0

    print(art_table.head())
    # art_table.loc[0, 'test'] += 0.1
    # print(art_table.loc[0, 'test'])

    # art_table.to_csv('./new_table.csv', encoding="utf-8-sig", index=False)

@api_view(['GET'])
def recommend_art(request, user_id):
    arts = Art.objects.filter(art_no__in=[1, 2])
    serializer = ArtSerializer(arts, many=True)
    return Response(serializer.data)
    
schedule.every(5).seconds.do(update_score)

while True:
    schedule.run_pending()
    time.sleep(1)