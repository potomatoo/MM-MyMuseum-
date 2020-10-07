export interface ArtistModule {
  artists: Artist[] | null;
  scrollEnd: boolean;
  searchText: string;
}

export interface Artist {
  artistName: string;
  artistAge: string;
  artistImg: string;
}
