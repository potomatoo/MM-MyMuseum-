from django.db import models

class Art(models.Model):
    art_no = models.IntegerField(primary_key=True)
    art_title = models.CharField(max_length=700, null=True, blank=True)
    art_artist = models.CharField(max_length=100, null=True, blank=True)
    art_age = models.CharField(max_length=200, null=True, blank=True)
    art_size = models.CharField(max_length=1000, null=True, blank=True)
    art_type = models.CharField(max_length=400, null=True, blank=True)
    art_method = models.CharField(max_length=1000, null=True, blank=True)
    art_museum = models.CharField(max_length=200, null=True, blank=True)
    art_url = models.CharField(max_length=120, null=True, blank=True)
    art_genre = models.CharField(max_length=60, null=True, blank=True)
    art_description = models.TextField(null=True, blank=True)
    
    class Meta:
        db_table = 'art'