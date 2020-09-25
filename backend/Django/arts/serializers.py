from .models import Art
from rest_framework import serializers

class ArtSerializer(serializers.ModelSerializer):
    class Meta:
        model = Art
        fields = '__all__' 