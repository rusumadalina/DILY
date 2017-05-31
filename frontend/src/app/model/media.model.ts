export class Media {
  public  mediaId : number;
  public memoryId: number;
  public mediaType: string;
  public mediaPath: string;

  constructor(object: any) {
    this.mediaId = object.mediaId;
    this.memoryId = object.memoryId;
    this.mediaType = object.mediaType;
    this.mediaPath = object.mediaPath;
  }
}
