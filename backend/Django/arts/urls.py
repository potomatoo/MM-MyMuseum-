from django.urls import path
from . import views

app_name = 'arts'

urlpatterns = [
    path('user/', views.recommend_art),
]