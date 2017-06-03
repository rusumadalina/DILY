/**
 * Created by rusum on 03.06.2017.
 */
export class FbMemory{
  public created_time: string;
  public description: string;
  public id: string;
  public attachments:  Object;
  constructor(object: any){
    this.created_time=object.created_time;
    this.description=object.description;
    this.id=object.id;
    this.attachments=object.attachments;
  }
}
