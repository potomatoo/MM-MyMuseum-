import { NormalizedScopedSlot } from "vue/types/vnode";

export interface Art {
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

export interface Artist {
  artistName: string | null;
  artistAge: string | null;
}

export interface Genre {
  genreName: string | null;
  genreDescription: string | null;
  genreUrl: string | null;
  genreKtitle: string | null;
  genreAge: string | null;
}

export interface Museum {
  museumName: string | null;
  museumCountry: string | null;
  museumDescription: string | null;
  museumUrl: string | null;
  museumLogo: string | null;
}

export interface DetailModule {
  artList: Art[] | null;
  art: Art | null;
  artist: Artist | null;
  museum: Museum | null;
  genre: Genre | null;
  scrollEnd: boolean;
  isFavoriteArt: boolean;
}
