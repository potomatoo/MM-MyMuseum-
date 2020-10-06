export interface MuseumModule {
  museums: Museum[] | null;
  scrollEnd: boolean;
  searchText: string;
}

export interface Museum {
  museumName: string;
  museumCountry: string;
  museumDescription: string;
  museumUrl: string;
}
