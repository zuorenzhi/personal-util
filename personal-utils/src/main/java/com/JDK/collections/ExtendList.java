package com.JDK.collections;

import java.util.*;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/2 9:56 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class ExtendList<E> extends ArrayList<E> {
    public ExtendList(int initialCapacity) {
        super(initialCapacity);
    }

    public ExtendList() {
        super();
    }

    public ExtendList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public void trimToSize() {
        super.trimToSize();
    }

    @Override
    public void ensureCapacity(int minCapacity) {
        super.ensureCapacity(minCapacity);
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
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return super.toArray(a);
    }

    @Override
    public E get(int index) {
        return super.get(index);
    }

    @Override
    public E set(int index, E element) {
        return super.set(index, element);
    }

    @Override
    public boolean add(E e) {
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    public E remove(int index) {
        return super.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return super.addAll(index, c);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public ListIterator<E> listIterator() {
        return super.listIterator();
    }

    @Override
    public Iterator<E> iterator() {
        return super.iterator();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return super.subList(fromIndex, toIndex);
    }

    public static void main(String[] args) {
        List<Integer> extendList = new ExtendList();

        Collections.addAll(extendList, 1, 2, 3, 45, 6);

        for (Integer integer : extendList) {
            System.out.println("integer = " + integer);
        }
        // TODO: 2017/6/2
        List<Integer> list = new ArrayList<>();

    }

    @Deprecated
    public static void test() {


    }
}
