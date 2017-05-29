/**
 * Created by Andra on 5/25/2017.
 */
export class Memory {
  public memoryId: number;
  public title: string;
  public description: string;
  public memoryLocation: string;
  public date: string;
  public privacy: string;
  public mainPicture: string;
  public tags: [string];
  constructor(object: any) {
    this.memoryId = object.memoryId;
    this.title = object.title;
    this.description = object.description;
    this.memoryLocation = object.memoryLocation;
    this.date = object.date;
    this.privacy = object.privacy;
    this.mainPicture = object.mainPicture;
    this.tags= object.tags;
  }
}
