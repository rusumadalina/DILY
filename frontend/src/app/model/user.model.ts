/**
 * Created by Andra on 5/15/2017.
 */
export class User {
  private _id: number;
  private _username: string;
  private _name : string;
  private _password: string;
  private _email: string;
  private _dateOfBirth: string;
  private _country: string;
  private _city: string;
  private _profilePicture: string;
  private _gender: string;

  constructor(id, username, name, password, email, dateOfBirth, country, city, profilePicture, gender) {
    this._id = id;
    this._username = username;
    this._name = name;
    this._password=password;
    this._email=email;
    this._dateOfBirth=dateOfBirth;
    this._country=country;
    this._city=city;
    this._profilePicture=profilePicture;
    this._gender=gender;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get dateOfBirth(): string {
    return this._dateOfBirth;
  }

  set dateOfBirth(value: string) {
    this._dateOfBirth = value;
  }

  get country(): string {
    return this._country;
  }

  set country(value: string) {
    this._country = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get profilePicture(): string {
    return this._profilePicture;
  }

  set profilePicture(value: string) {
    this._profilePicture = value;
  }

  get gender(): string {
    return this._gender;
  }

  set gender(value: string) {
    this._gender = value;
  }
}
