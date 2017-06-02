/**
 * Created by rusum on 02.06.2017.
 */
export class Document{
  public documentId : number;
  public userId: number;
  public path: string;

  constructor(object: any){
   this.documentId=object.documentId;
   this.userId=object.documentId;
   this.path=object.path;
  }
}
