from django.urls import path
from . import views

app_name = 'arts'

urlpatterns = [
    path('user/<str:user_id>/', views.recommend_art),
    # path('ttest/', views.test),
]