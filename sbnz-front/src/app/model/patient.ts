import { Medicine } from "./medicine";

export interface Patient{
    name: string;
    lastName: string;
    email: string;
    medicineAlergies: Medicine[];
    componentAlergies: Medicine[];

    
}