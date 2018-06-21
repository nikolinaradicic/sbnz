import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(items: any[], searchText: string): any {
    if(!items) return [];
    if(!searchText) return items;
  searchText = searchText.toLowerCase();
  if (items[0].email){
    //filter pacijenata
    return items.filter( it => {
      return it.name.toLowerCase().includes(searchText)
       || it.lastName.toLowerCase().includes(searchText)
       || it.email.toLowerCase().includes(searchText);
    });
  }
  return items.filter( it => {
      return it.name.toLowerCase().includes(searchText);
    });
  }

}
