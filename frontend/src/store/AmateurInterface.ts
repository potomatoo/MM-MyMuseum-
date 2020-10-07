export interface AmateurModule {
  amateurs: Amateur[] | null;
  scrollEnd: boolean;
  searchText: string;
}

export interface Amateur {
  userId: string;
  userPassword: string;
  userType: number;
  userName: string;
  userArtist: number;
}
