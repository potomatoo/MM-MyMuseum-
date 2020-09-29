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

export interface DetailModule {
  artList: Art[] | null;
  art: Art | null;
}
