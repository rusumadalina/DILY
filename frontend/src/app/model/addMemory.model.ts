/**
 * Created by rusum on 01.06.2017.
 */
export class AddMemory {

  public title: string;
  public description: string;
  public memoryLocation: string;
  public date: string;
  public privacy: string;
  public mainPicture: string;


  constructor(title: string, description: string, memoryLocation: string, date: string, privacy: string, mainPicture: string) {
    this.title = title;
    this.description = description;
    this.memoryLocation = memoryLocation;
    this.date = date;
    this.privacy = privacy;
    this.mainPicture = mainPicture;
  }
}
