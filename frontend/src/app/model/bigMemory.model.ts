import {User} from "./user.model";
import {Tag} from "./tag.model";
import {Media} from "./media.model";

export class BigMemory {
  public memoryId: number;
  public title: string;
  public description: string;
  public memoryLocation: string;
  public date: string;
  public privacy: string;
  public mainPicture: string;
  public taggedFriends: [User];
  public tags: [Tag];
  public media: [Media];


  constructor(object:any) {
    this.memoryId = object.memoryId;
    this.title = object.title;
    this.description = object.description;
    this.memoryLocation = object.memoryLocation;
    this.date = object.date;
    this.privacy = object.privacy;
    this.mainPicture = object.mainPicture;
    this.taggedFriends = object.taggedFriends;
    this.tags = object.tags;
    this.media = object.media;
  }
}
