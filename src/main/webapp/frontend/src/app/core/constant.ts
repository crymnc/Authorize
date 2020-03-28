import {Deserializable} from "../model/interface/deserializablel";

export class Constant implements Deserializable{
  constructor(public id:number, public name:string){}

  deserialize(input: any): this {
    Object.assign(this , input);
    return this;
  }
}
