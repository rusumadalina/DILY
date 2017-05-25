/**
 * Created by Andra on 5/25/2017.
 */
export class Memory {
  public memoryid: number;
  public title: string;
  public description: string;
  public memorylocation: string;
  public datem: string;
  public privacy: string;
  public mainpicture: string;

  constructor(object: any) {
    this.memoryid = object.memoryid;
    this.title = object.title;
    this.description = object.description;
    this.memorylocation = object.memorylocation;
    this.datem = object.datem;
    this.privacy = object.privacy;
    this.mainpicture = object.mainpicture
  }
}
