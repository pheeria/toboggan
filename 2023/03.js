const fs = require("fs");
const path = require("path");


fs.readFile(path.join(__dirname, "./03.txt"), "utf8", (err, str) => {
    if(err) {
        console.error(err);
        return;
    }

    const arr = str.split("\n");

    console.log(part1(arr));
})


function part1(arr) {
    let sum = 0;

    const digitsAndDots = "1234567890.";

    arr.forEach((line, x) => {
        const numbers = line.matchAll(/\d+/g);

        Array.from(numbers).forEach((num) => {
            const number = num[0];
            const numberIndex = num['index'];
            let isValid = false;
            
            for(let digitIndex = 0; digitIndex < number.length; digitIndex++ ){
                const y = numberIndex + digitIndex;

                if(
                    !digitsAndDots.includes(arr?.[x-1]?.[y-1] || ".") 
                    || !digitsAndDots.includes(arr?.[x+1]?.[y+1] || ".") 
                    || !digitsAndDots.includes(arr?.[x-1]?.[y+1] || ".") 
                    || !digitsAndDots.includes(arr?.[x+1]?.[y-1] || ".") 
                    || !digitsAndDots.includes(arr?.[x-1]?.[y] || ".") 
                    || !digitsAndDots.includes(arr?.[x+1]?.[y] || ".") 
                    || !digitsAndDots.includes(arr?.[x]?.[y-1] || ".")
                    || !digitsAndDots.includes(arr?.[x]?.[y+1] || ".") 
                ) {
                    isValid = true;
                    break;
                }
            };

            if(isValid) {
                sum += Number(number);
            }

        });
    });

    return sum;
}

console.log("part1: ", part1([
    "467..114..",
    "...*......",
    "..35..633.",
    "......#...",
    "617*......",
    ".....+.58.",
    "..592.....",
    "......755.",
    "...$.*....",
    ".664.598.."
]));