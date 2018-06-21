import { Medicine } from "./medicine";

export interface Patient{
    id?: number;
    name: string;
    lastName: string;
    email: string;
    medicineAlergies: Medicine[];
    componentAlergies: Medicine[];

    
}