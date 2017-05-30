export class Friend {
  public friendId: number;
  public username: string;
  public profilePicture: string;
  public name: string;
  public city: string;
  public country: string;
  public dateFriends: string;


  constructor(object: any) {
    this.friendId= object.friendId;
    this.username=object.username;
    this.profilePicture = object.profilePicture;
    this.name = object.name;
    this.city = object.city;
    this.country = object.country;
    this.dateFriends = object.dateFriends;
  }
}
