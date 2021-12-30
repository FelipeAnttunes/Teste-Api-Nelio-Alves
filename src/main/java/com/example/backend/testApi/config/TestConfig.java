package com.example.backend.testApi.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.backend.testApi.entities.Category;
import com.example.backend.testApi.entities.Order;
import com.example.backend.testApi.entities.OrderItem;
import com.example.backend.testApi.entities.Payment;
import com.example.backend.testApi.entities.Product;
import com.example.backend.testApi.entities.User;
import com.example.backend.testApi.entities.enums.OrderStatus;
import com.example.backend.testApi.repositories.CategoryRepository;
import com.example.backend.testApi.repositories.OrderItemRepository;
import com.example.backend.testApi.repositories.OrderRepository;
import com.example.backend.testApi.repositories.ProductRepository;
import com.example.backend.testApi.repositories.UserRepository;

//classe usada para popular o nosso banco de dados utilizando objetos. //

@Configuration // definindo classe especifica de configuração da nossa aplicação. //
@Profile("test") // definindo que se trata de uma classe especifica p/ config de test. //
public class TestConfig implements CommandLineRunner {

	// fazendo a injeção de depencia para acessar o repository e salvar os dados. //

	// declarando atributo e associando uma instancia do repository ao config. //
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override // metodo usado para executar essa classe qnd o programa for iniciado. //
	public void run(String... args) throws Exception {

		// categorias dos produtos //
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		// produtos com suas devidas definiçoes. //
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		// salvando as categorias e produtos criados no banco de dados. //
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		// fazendo associações de categoria e produtos. //
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		// salvando no banco de dados os abjetos ja com as associações feitas. //
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		// usuarios / clientes. //
		User u1 = new User(null, "Usuario1", "usuario1@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Usuario2", "usuario2@gmail.com", "977777777", "123456");

		// pedidos com suas caracteristicas. //
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		// salvando os usuarios e pedidos criados no banco de dados. //
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		// fazendo a relação dos itens de pedido c/ produto, quantidade e o preço. //
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		// fazendo a associação de mao dupla para salvar o pagamento no pedido
		// criando essa nova instancia nao tendo que chamar o repository dessa
		// classe e sim fazendo o save juntamente com a propria classe
		// associada. //
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		

	}
}
