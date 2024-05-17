import {dictionary} from '../dictionary/dictionary.js';

export  const getText = (key) => {
    return dictionary[key] || dictionary['nothing'];
}