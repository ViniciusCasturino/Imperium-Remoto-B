package br.edu.atitus.product_service.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.product_service.dtos.ProductDTO;
import br.edu.atitus.product_service.entities.ProductEntity;
import br.edu.atitus.product_service.repositories.ProductRepository;

@RestController
@RequestMapping("/ws/products")
public class WsProductControllers {

    private final ProductRepository repository;
    
    public WsProductControllers(ProductRepository repository) {
    	super();
    	this.repository = repository;
    }
    
    private ProductEntity convertDto2Entity(ProductDTO dto) {
    	var product = new ProductEntity();
    	BeanUtils.copyProperties(dto, product);
    	return product;
    }
    
    @PostMapping
    public ResponseEntity<ProductEntity> post(
    		@RequestBody ProductDTO dto,
    		@RequestHeader("X-User-id") Long idUser,
    		@RequestHeader("X-User-Email")String userEmail,
    		@RequestHeader("X-User-Type")Integer userType) throws Exception{
    	if (userType != 0)
    		throw new AuthenticationException("Usuário sem permissão");
    	
    	
    	//somente adm
    	
    	var product = convertDto2Entity(dto);
    	product.setStock(10);
        repository.save(product);
        
        return ResponseEntity.status(201).body(product);
    }
    		
    
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handlerAuth(AuthenticationException e){
    	String message = e.getMessage().replaceAll("[\\rzzn]", "");
    	return ResponseEntity.status(403).body(message);
    }
    
    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductEntity> put(
    		@PathVariable Long idProduct,
    		@RequestBody ProductDTO dto,
    		@RequestHeader("X-User-id") Long idUser,
    		@RequestHeader("X-User-Email")String userEmail,
    		@RequestHeader("X-User-Type")Integer userType) throws Exception{
    	if (userType != 0)
    		throw new AuthenticationException("Usuário sem permissão");
    	
    	
    	//somente adm
    	
    	var product = convertDto2Entity(dto);
    	product.setId(idProduct);
    	product.setStock(10);
        repository.save(product);
        
        return ResponseEntity.status(200).body(product);
    }
    
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<String> delete(
    		@PathVariable Long idProduct,
    		@RequestHeader("X-User-id") Long idUser,
    		@RequestHeader("X-User-Email")String userEmail,
    		@RequestHeader("X-User-Type")Integer userType) throws Exception{
    	if (userType != 0)
    		throw new AuthenticationException("Usuário sem permissão");
    	
    	repository.deleteById(idProduct);
        
        return ResponseEntity.ok("Produto Deletado");
    
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
    
////    private final CurrencyClient currencyClient;
////    private final CacheManager cacheManager;
//
//    public WsProductControllers(ProductRepository repository, CurrencyClient currencyClient, CacheManager cacheManager) {
//        super();
//        this.repository = repository;
//        this.currencyClient = currencyClient;
//        this.cacheManager = cacheManager;
//    }
//
//    @Value("${server.port}")
//    private int port;
//
//    @GetMapping("/{idProduct}/{targetCurrency}")
//    public ResponseEntity<ProductEntity> getProduct(
//            @PathVariable Long idProduct,
//            @PathVariable String targetCurrency) throws Exception {
//
//		targetCurrency = targetCurrency.toUpperCase();
//		String nameCache = "Product";
//		String keyCache = idProduct + targetCurrency;
//
//		ProductEntity product = cacheManager.getCache(nameCache).get(keyCache, ProductEntity.class);
//		if (product == null) {
//			product = repository.findById(idProduct)
//					.orElseThrow(() -> new Exception("Product not found"));
//
//			product.setEnviroment("Product-service running on Port: " + port);
//
//			if (product.getCurrency().equals(targetCurrency)) {
//				product.setConvertedPrice(product.getPrice());
//			} else {
//				CurrencyResponse currency = currencyClient.getCurrency(product.getPrice(), product.getCurrency(), targetCurrency);
//				if (currency != null) {
//					product.setConvertedPrice(currency.getConvertedValue());
//					product.setEnviroment(product.getEnviroment() + " - " + currency.getEnviroment());
//
//					cacheManager.getCache(nameCache).put(keyCache, product);
//				} else {
//					product.setConvertedPrice(-1);
//					product.setEnviroment(product.getEnviroment() + " - Currency unavalaible");
//				}
//			}
//		}else {
//			product.setEnviroment("Product-service running on Port: " + port + " - DataSource: cache");
//		}
//			return ResponseEntity.ok(product);
//		}
//
//		@GetMapping("/noconverter/{idProduct}")
//		public ResponseEntity<ProductEntity> getNoConverter(@PathVariable Long idProduct) throws Exception {
//			var product = repository.findById(idProduct).orElseThrow(() -> new Exception("Produto não encontrado"));
//			product.setConvertedPrice(-1);
//			product.setEnviroment("Product-service running on Port: " + port);
//			return ResponseEntity.ok(product);
//		}
//
//		@GetMapping("/{targetCurrency}")
//		public  ResponseEntity<Page<ProductEntity>> getAllProducts(
//				@PathVariable String targetCurrency,
//				@PageableDefault(page = 0, size = 5, sort = "description", direction = Sort.Direction.ASC)
//					Pageable pageable) throws Exception {
//			Page<ProductEntity> products = repository.findAll(pageable);
//			for (ProductEntity product : products){
//				CurrencyResponse currency = currencyClient.getCurrency(product.getPrice(),product.getCurrency(),targetCurrency);
//
//				product.setConvertedPrice(currency.getConvertedValue());
//				// Setar ambiente
//				product.setEnviroment("Product-Service running on port: " + port + " - " + currency.getEnviroment()); // + " - " + cambio.getAmbiente());
//			}
//			return  ResponseEntity.ok(products);
//		}
//	}