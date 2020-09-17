export interface ArticleModule {
  articles: Article[] | null;
}

export interface Article {
  title: string;
  author?: string | null;
  category?: string | null;
  hero: string;
  prominent?: boolean | null;
}
