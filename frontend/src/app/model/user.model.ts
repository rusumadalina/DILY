/**
 * Created by Andra on 5/15/2017.
 */
export class User {
  public _user_id: number;
  public _username: string;
  public _name: string;
  public _password: string;
  public _email: string;
  private _dateOfBirth: string;
  private _country: string;
  private _city: string;
  private _profilePicture: string;
  private _gender: string;

  constructor() {
    this._user_id = JSON.parse(localStorage.getItem('user'))['user_id'];
    this._username =  JSON.parse(localStorage.getItem('user'))['username'];
    this._name =  JSON.parse(localStorage.getItem('user'))['name'];
    this._password =  JSON.parse(localStorage.getItem('user'))['password'];
    this._email = JSON.parse(localStorage.getItem('user'))['email'];
    this._dateOfBirth = JSON.parse(localStorage.getItem('user'))['dateOfBirth'];
    this._country = JSON.parse(localStorage.getItem('user'))['country'];
    this._city =  JSON.parse(localStorage.getItem('user'))['city'];
    this._profilePicture =  JSON.parse(localStorage.getItem('user'))['profilePicture'];
    this._gender =  JSON.parse(localStorage.getItem('user'))['gender'];
  }

  getUsername() {
    return this._username;
  }
}
