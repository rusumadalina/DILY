export class Friend {
  public profilePicture: string;
  public name: string;
  public city: string;
  public country: string;
  public datefriends: string;


  constructor(object: any) {
    this.profilePicture = object.profilePicture;
    this.name = object.name;
    this.city = object.city;
    this.country = object.country;
    this.datefriends = object.datefriends;
  }
}
