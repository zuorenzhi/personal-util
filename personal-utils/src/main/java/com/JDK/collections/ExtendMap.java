package com.JDK.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExtendMap extends HashMap {

	public ExtendMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public ExtendMap(int initialCapacity) {
		super(initialCapacity);
	}

	public ExtendMap() {
		super();
	}

	public ExtendMap(Map m) {
		super(m);
	}

	@Override
	public int size() {
		return super.size();
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public Object get(Object key) {
		return super.get(key);
	}

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key);
	}

	@Override
	public Object put(Object key, Object value) {
		return super.put(key, value);
	}

	@Override
	public void putAll(Map m) {
		super.putAll(m);
	}

	@Override
	public Object remove(Object key) {
		return super.remove(key);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public boolean containsValue(Object value) {
		return super.containsValue(value);
	}

	@Override
	public Object clone() {
		return super.clone();
	}

	@Override
	public Set keySet() {
		return super.keySet();
	}

	@Override
	public Collection values() {
		return super.values();
	}

	@Override
	public Set<Map.Entry> entrySet() {
		return super.entrySet();
	}
}
