export class Tag{
  public tagId : number;
  public tagName: string;

  constructor(object: any){
    this.tagId = object.tagId;
    this.tagName = object.tagName;
  }
}
