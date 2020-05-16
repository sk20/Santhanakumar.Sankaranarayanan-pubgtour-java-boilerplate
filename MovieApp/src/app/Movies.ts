export class Movies {
    movieId: String
    name: String
    desc: string
    poster_path: string
    isSelected: boolean
    constructor(obj?: any) {
        this.movieId=obj && obj.movieId || null;
        this.name=obj && obj.name || null;
        this.desc=obj && obj.desc || null;
        this.poster_path=obj && obj.poster_path || null;
    }
   }