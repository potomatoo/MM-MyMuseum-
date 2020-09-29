export interface Art {
  artNo: number;
  artTitle: string;
  artArtist: string;
  artAge: string;
  artSize: string;
  artType: string;
  artMehtod: string;
  artMuseum: string;
  artUrl: string;
  artGenre: string;
  artDescription: string;
  art_color: string;
}

export interface DetailModule {
  artList: Art[] | null;
}
