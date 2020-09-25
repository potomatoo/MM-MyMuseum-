from django.urls import path
from . import views

app_name = 'arts'

urlpatterns = [
    path('<str:user_id>/recommend/', views.recommend_art),
    path('test/', views.test),
]