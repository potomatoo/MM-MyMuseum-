export interface AmateurModule {
  amateurs: Amateur[] | null;
  amateurArts: AmateurArt[] | null;
  scrollEnd: boolean;
  searchText: string;
  userId: string;
  userName: string;
  uploadData: UploadData | null;
}

export interface Amateur {
  userId: string;
  userPassword: string;
  userType: number;
  userName: string;
  userArtist: number;
}

export interface AmateurArt {
  mysarNo: number;
  myartTitle: string;
  userId: string;
  myartAge: string;
  myartType: string;
  myartUrl: string;
  myartDescription: string;
}

export interface UploadData {
  token: string;
  description: string;
  file: File;
  title: string;
  type: string;
}
