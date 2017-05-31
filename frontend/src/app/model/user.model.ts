/**
 * Created by Andra on 5/15/2017.
 */
export class User {
  public user_id: number;
  public username: string;
  public name: string;
  public password: string;
  public email: string;
  public dateOfBirth: string;
  public country: string;
  public city: string;
  public profilePicture: string;
  public gender: string;
  constructor(object: any){
    this.user_id = object.user_id;
    this.username=object.username;
    this.name=object.name;
    this.password=object.password;
    this.email=object.email;
    this.dateOfBirth=object.dateOfBirth;
    this.country=object.country;
    this.city=object.city;
    this.profilePicture=object.profilePicture;
    this.gender=object.gender;
  }
}
