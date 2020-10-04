export interface RecommendationModule {
  arts: Art[] | null;
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
  click_type: number;
}
