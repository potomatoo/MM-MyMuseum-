export interface AccountsModule {
  user: User | null;
  token: string | null;
}

export interface User {
  userId: string | null;
  userPassword: string | null;
  userType: number | null;
  UserAge: number | null;
  userGender: number | null;
}
