const input = [
	[1, 1, 2],
	[2, 2, 0.5],
	[-1, -3, 2],
	[5, 2, 1]
];

let lowestX = 0,
	highestX = 0,
	lowestY = 0,
	highestY = 0;

for(let arr of input) {
	let x = arr[0],
		y = arr[1],
		r = arr[2];
	if((x - r) < lowestX) {
		console.log('changing lowestX');
		lowestX = (x - r);
	}
	if((x + r) > highestX) {
		console.log('changing highestX');
		highestX = (x + r);
	}
	if((y - r) < lowestY) {
		console.log('changing lowestY');
		lowestY = (y - r);
	}
	if((y + r) > highestY) {
		console.log('changing highestY');
		highestY = (y + r);
	}
}
/*
a------------b
|            |
|            |
|            |
c------------d
*/
let ans = {
	a: {x: lowestX, y: highestY},
	b: {x: highestX, y: highestY},
	c: {x: lowestX, y: lowestY},
	d: {x: highestX, y: lowestY}
};

console.log(ans);
