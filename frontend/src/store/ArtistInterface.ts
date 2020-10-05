export interface ArtistModule {
  artists: Artist[] | null;
  scrollEnd: boolean;
}

export interface Artist {
  artistName: string;
  artistAge: string;
  artistImg: string;
}
