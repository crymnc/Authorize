import {Deserializable} from "./interface/deserializablel";
import {Role} from "./role";

export class User implements Deserializable{

  id : number;
  name : string;
  lastName : string;
  username : string;
  active: boolean;
  lastActivationDate: any;
  roles:Array<Role> = new Array<Role>();

  deserialize(input: any): this {
    Object.assign(this , input);
    input['roles[]'].map(val => this.roles.push(new Role(val.id,val.name)))
    return this;
  }

}
