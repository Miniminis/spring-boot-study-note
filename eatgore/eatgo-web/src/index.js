(async () => {
    const url = 'http://localhost:8080/restaurants';
    const response = await fetch(url);
    const restaurants = await response.json();
    //console.log(restaurants);

    const element = document.getElementById('app');
    element.innerHTML = `
        ${restaurants.map( restaurants => `
            <p>
                ${restaurants.id}
                ${restaurants.name}        
            </p>
        `).join('')}
        `;
})();