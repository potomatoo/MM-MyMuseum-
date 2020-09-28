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
import time


def test_hello():
    print('hello')
    count += 1
    threading.Timer(10, test_hello).start()

test_hello()

@api_view(['GET'])
def recommend_art(request, user_id):
    pass

@api_view(['GET'])
def test(request):
    print(count)
    return Response({'test': 'hi'})
    
count = 0