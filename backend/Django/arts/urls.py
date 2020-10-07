from django.urls import path
from . import views

app_name = 'arts'

urlpatterns = [
    # score
    path('user/', views.recommend_art),

    # color
    path('color/yellow/', views.recommend_yellow),
    path('color/blue/', views.recommend_blue),
    path('color/brown/', views.recommend_brown),
    path('color/green/', views.recommend_green),
    path('color/black/', views.recommend_black),
    path('color/teal/', views.recommend_teal),
    path('color/red/', views.recommend_red),
    path('color/orange/', views.recommend_orange),
    path('color/pink/', views.recommend_pink),
    path('color/gray/', views.recommend_gray),
    path('color/white/', views.recommend_white),

    # time
    # path('time/', views.recommend_time),

    # weather
    path('weather/', views.recommend_weather),
]