export interface AmateurModule {
  amateurs: Amateur[] | null;
  amateurArts: AmateurArt[] | null;
  scrollEnd: boolean;
  searchText: string;
  userId: string;
  userName: string;
  detail: AmateurArt | null;
}

export interface Amateur {
  userId: string;
  userPassword: string;
  userType: number;
  userName: string;
  userArtist: number;
}

export interface AmateurArt {
  myartNo: number;
  myartTitle: string;
  userId: string;
  myartAge: string;
  myartType: string;
  myartUrl: string;
  myartDescription: string;
}
