(async () =>{
   const url= 'http://localhost:8080/restaurants';
   const response = await fetch(url);
   const data= await response.json();
   console.log(data);

   const element = document.getElementById('app');
   element.innerHTML = `
        ${data.map(data => `
            <p>
                ${data.id}
                ${data.name}
                ${data.address}
            </p>
        `).join('')} 
        
    `;

})();