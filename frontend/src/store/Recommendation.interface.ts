export interface RecommendationModule {
  arts: Art[] | null;
  artsByColor: Art[] | null;
  artsByWeather: ArtsByWeather | null;
  artsByTime: ArtsByTime | null;
  favoriteArts: FavoriteArt[] | null;
  scrollEnd: boolean;
  favoriteArt: FavoriteArt | null;
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

export interface FavoriteArt {
  artNo: number;
  artTitle: string | null;
  artArtist: string | null;
  artAge: string | null;
  artSize: string | null;
  artType: string | null;
  artMehtod: string | null;
  artMuseum: string | null;
  artUrl: string;
  artGenre: string | null;
  artDescription: string | null;
  art_color: string | null;
}
