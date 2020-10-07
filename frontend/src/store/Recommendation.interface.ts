export interface RecommendationModule {
  arts: Art[] | null;
  artsByColor: Art[] | null;
  artsByWeather: ArtsByWeather | null;
  artsByTime: ArtsByTime | null;
  favoriteArts: Art[] | null;
  scrollEnd: boolean;
}

export interface ArtsByWeather {
  title: string;
  data: Art[];
}

export interface ArtsByTime {
  title: string;
  data: Art[];
}

export interface Art {
  art_title: string;
  art_artist: string;
  art_genre: string;
  art_description: string;
  art_age: string;
  art_museum: string;
  art_type: string;
  art_url: string;
  art_size: string;
  art_no: number;
  art_method: string;
  log_type?: number;
}
