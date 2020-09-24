from django.db import models

class Art(models.Model):
    id = models.IntegerField(primary_key=True)
    art_name = models.CharField(max_length=200)
    art_artist = models.CharField(max_length=200)
    art_url = models.TextField()
    # art_style = models.CharField(max_length=200)
    # art_size = models.CharField(max_length=200)
    # art_museum = models.CharField(max_length=200)
    # art_genre = models.CharField(max_length=200)
    # art_age = models.CharField(max_length=200)
    # art_method = models.CharField(max_length=200)
    # art_color = models.CharField(max_length=200)
    # art_detail = models.TextField()