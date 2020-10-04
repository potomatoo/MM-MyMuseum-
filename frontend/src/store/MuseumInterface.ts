export interface MuseumModule {
  museums: Museum[] | null;
}

export interface Museum {
  museumName: string;
  museumCountry: string;
  museumDescription: string;
  museumUrl: string;
}
