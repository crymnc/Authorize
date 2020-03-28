import {Adapter} from "./adapter";
import {Constant} from "../constant";
import {Injectable} from "@angular/core";
@Injectable({
  providedIn: "root"
})
export class ConstantAdapter implements Adapter<Constant>{
  adapt(item: any): Constant {
    return new Constant(item.id, item.name);
  }

}
