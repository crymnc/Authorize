import {Constant} from "../core/constant";
import {UserComponent} from "./user.component";

export class Role extends Constant{

  userComponents: Array<UserComponent> = new Array<UserComponent>();

  constructor(){
    super();
  }

}

